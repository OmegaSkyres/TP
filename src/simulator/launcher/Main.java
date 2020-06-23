package simulator.launcher;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import simulator.control.Controller;
import simulator.exceptions.WrongValuesContamination;
import simulator.exceptions.WrongValuesException;
import simulator.exceptions.WrongValuesWeather;
import simulator.factories.*;
import simulator.model.*;
import simulator.view.MainWindow;

import javax.swing.*;

public class Main {

	private final static Integer _timeLimitDefaultValue = 10;
	private static String _inFile = null;
	private static String _outFile = null;
	private static int _timeLimit = 0;
	private static Factory<Event> _eventsFactory = null;
	private static ModoEjecucion modo = null;

	private enum ModoEjecucion {
		CONSOLE("console"), GUI("gui");
		private String descModo;
		private ModoEjecucion(String modeDesc) {
			descModo = modeDesc;
		}
		private String getModelDesc() {
			return descModo;
		}
	}

	private static void parseaOpcionModo(CommandLine commandLine){
		String mode = commandLine.getOptionValue("m");

		if(mode == null ||mode.equals(ModoEjecucion.CONSOLE.getModelDesc()))
			Main.modo = ModoEjecucion.CONSOLE;

		else if(mode.equals("gui"))
			Main.modo = ModoEjecucion.GUI;

		else new ParseException("El modo introducido no es compatible");
	}



	private static void parseArgs(String[] args) {

		// define the valid command line options
		//
		Options cmdLineOptions = buildOptions();

		// parse the command line as provided in args
		//
		CommandLineParser parser = new DefaultParser();
		try {
			CommandLine line = parser.parse(cmdLineOptions, args);
			parseHelpOption(line, cmdLineOptions);
			parseaOpcionModo(line);
			parseInFileOption(line);
			parseOutFileOption(line);
			parseTicksOption(line);

			// if there are some remaining arguments, then something wrong is
			// provided in the command line!
			//
			String[] remaining = line.getArgs();
			if (remaining.length > 0) {
				String error = "Illegal arguments:";
				for (String o : remaining)
					error += (" " + o);
				throw new ParseException(error);
			}

		} catch (ParseException e) {
			System.err.println(e.getLocalizedMessage());
			System.exit(1);
		}

	}

	private static Options buildOptions() {
		Options cmdLineOptions = new Options();

		cmdLineOptions.addOption(Option.builder("i").longOpt("input").hasArg().desc("Events input file").build());
		cmdLineOptions.addOption(Option.builder("o").longOpt("output").hasArg().desc("Output file, where reports are written.").build());
		cmdLineOptions.addOption(Option.builder("t").longOpt("ticks").hasArg().desc("Ticks to the simulator’s main loop (default value is 10)").build());
		cmdLineOptions.addOption(Option.builder("h").longOpt("help").desc("Print this message").build());
		cmdLineOptions.addOption(Option.builder("m").longOpt("ticks").hasArg().desc("'batch' for batch mode and 'gui' for GUI mode (defaul value is batch)").build());

		return cmdLineOptions;
	}

	private static void parseHelpOption(CommandLine line, Options cmdLineOptions) {
		if (line.hasOption("h")) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp(Main.class.getCanonicalName(), cmdLineOptions, true);
			System.exit(0);
		}
	}

	private static void parseInFileOption(CommandLine line) throws ParseException {
		_inFile = line.getOptionValue("i");
	}

	private static void parseOutFileOption(CommandLine line) throws ParseException {
		_outFile = line.getOptionValue("o");
	}

	private static void parseTicksOption(CommandLine line) throws ParseException {
		_timeLimit = (line.getOptionValue("t") != null)? Integer.parseInt(line.getOptionValue("t")) : _timeLimitDefaultValue;
	}

	private static void initFactories() {
		List<Builder<LightSwitchStrategy>> listast = new ArrayList<>();
		listast.add(new RoundRobinStrategyBuilder());
		listast.add(new MostCrowdedStrategyBuilder());
		Factory<LightSwitchStrategy> factorySs = new BuilderBasedFactory<>(listast);

		List<Builder<DequeingStrategy>> listadq = new ArrayList<>();
		listadq.add(new MoveAllStrategyBuilder());
		listadq.add(new MoveFirstStrategyBuilder());
		Factory<DequeingStrategy> factoryDq = new BuilderBasedFactory<>(listadq);

		List<Builder<Event>> listaEvents = new ArrayList<>();
		listaEvents.add(new NewInterCityRoadEventBuilder());
		listaEvents.add(new NewCityRoadEventBuilder());
		listaEvents.add(new NewJunctionEventBuilder(factorySs,factoryDq));
		listaEvents.add(new NewVehicleEventBuilder());
		listaEvents.add(new SetContClassEventBuilder());
		listaEvents.add(new SetWeatherEventBuilder());
		Factory<Event> factoryEvents = new BuilderBasedFactory<>(listaEvents);

		_eventsFactory = factoryEvents;


	}

	private static void startBatchMode() throws Exception {
		InputStream in = new FileInputStream(new File(_inFile));
		OutputStream out = _outFile == null ?
				System.out : new FileOutputStream(new File(_outFile));
		TrafficSimulator sim = new TrafficSimulator();
		Controller ctrl = new Controller(sim, _eventsFactory);
		ctrl.loadEvents(in);
		ctrl.run(_timeLimit, out);
		in.close();
		System.out.println("Done!");
	}

	private static void startGUIMode() throws Exception {
		InputStream in =  _inFile == null ?
				System.in : new FileInputStream(new File(_inFile));
		OutputStream out = _outFile == null ?
				System.out : new FileOutputStream(new File(_outFile));
		TrafficSimulator sim = new TrafficSimulator();
		Controller ctrl = new Controller(sim, _eventsFactory, _timeLimit, in, out);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainWindow(ctrl);
				try {
					if(_inFile != null)
					ctrl.loadEvents(in);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});


	}

	private static void start(String[] args) throws Exception {
		initFactories();
		parseArgs(args);
		if(modo == ModoEjecucion.CONSOLE){
			startBatchMode();
		}
		else if(modo == ModoEjecucion.GUI){
			startGUIMode();
		}

	}

	// example command lines:
	//
	// -i resources/examples/ex1.json
	// -i resources/examples/ex1.json -t 300
	// -i resources/examples/ex1.json -o resources/tmp/ex1.out.json
	// --help

	public static void main(String[] args) {
		try {
			start(args);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
