package teetime.framework;

/**
 * The <code>ProducerStage</code> produces at least one element at each execution.<br>
 *
 * @author Christian Wulf
 *
 * @param <O>
 *            the type of the default output port
 *
 */
public abstract class AbstractProducerStage<O> extends AbstractStage {

	protected final OutputPort<O> outputPort = this.createOutputPort();

	public final OutputPort<O> getOutputPort() {
		return this.outputPort;
	}

	@Override
	public void executeWithPorts() {
		this.execute();
	}

	@Override
	public TerminationStrategy getTerminationStrategy() {
		return TerminationStrategy.BY_SELF_DECISION;
	}

	protected abstract void execute();

}
