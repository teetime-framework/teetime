package teetime.examples.quicksort;

import teetime.framework.Configuration;
import teetime.framework.DynamicConfigurationContext;
import teetime.stage.quicksort.QuicksortStage;

public class QuicksortConfiguration extends Configuration {

	public QuicksortConfiguration(final int[] arr) {

		final ArrayToQuicksortProblem ltqsp = new ArrayToQuicksortProblem(arr);
		final QuicksortStage qsort = new QuicksortStage(new DynamicConfigurationContext());
		final QuicksortProblemToList qsptl = new QuicksortProblemToList();

		connectPorts(ltqsp.getOutputPort(), qsort.getInputPort());
		connectPorts(qsort.getOutputPort(), qsptl.getInputPort());

	}

}
