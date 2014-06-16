package teetime.examples.throughput.methodcall;

import teetime.util.list.CommittableQueue;

public abstract class ConsumerStage<I, O> extends AbstractStage<I, O> {

	@Override
	public CommittableQueue<O> execute2(final CommittableQueue<I> elements) {
		boolean inputIsEmpty = elements.isEmpty();
		if (inputIsEmpty) {
			this.disable();
			return this.outputElements;
		}

		CommittableQueue<O> output = super.execute2(elements);
		this.setReschedulable(!elements.isEmpty()); // costs ~1200 ns on chw-work
		return output;
	}

	@Override
	public void executeWithPorts() {
		I element = this.getInputPort().receive();
		this.execute5(element);
	}

}
