package teetime.framework.scheduling;

import teetime.framework.core.IStage;

public final class UnsynchronizedStageStateContainer extends StageStateContainer {

	private int numOpenedInputPorts;

	public UnsynchronizedStageStateContainer(final IStage stage) {
		super(stage);
		this.numOpenedInputPorts = stage.getInputPorts().size();
	}

	@Override
	public int decNumOpenedPorts() {
		return --this.numOpenedInputPorts;
	}

}
