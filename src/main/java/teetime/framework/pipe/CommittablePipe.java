package teetime.framework.pipe;

import teetime.framework.AbstractIntraThreadPipe;
import teetime.framework.InputPort;
import teetime.framework.OutputPort;
import teetime.util.list.CommittableResizableArrayQueue;

public final class CommittablePipe extends AbstractIntraThreadPipe {

	private final CommittableResizableArrayQueue<Object> elements = new CommittableResizableArrayQueue<Object>(null, 4);

	<T> CommittablePipe(final OutputPort<? extends T> sourcePort, final InputPort<T> targetPort) {
		super(sourcePort, targetPort);
	}

	@Deprecated
	public static <T> void connect(final OutputPort<? extends T> sourcePort, final InputPort<T> targetPort) {
		final IPipe pipe = new CommittablePipe(null, null);
		pipe.connectPorts(sourcePort, targetPort);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see teetime.examples.throughput.methodcall.IPipe#add(T)
	 */
	@Override
	public boolean add(final Object element) {
		this.elements.addToTailUncommitted(element);
		this.elements.commit();
		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see teetime.examples.throughput.methodcall.IPipe#removeLast()
	 */
	@Override
	public Object removeLast() {
		final Object element = this.elements.removeFromHeadUncommitted();
		this.elements.commit();
		return element;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see teetime.examples.throughput.methodcall.IPipe#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return this.elements.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see teetime.examples.throughput.methodcall.IPipe#readLast()
	 */
	@Override
	public Object readLast() {
		return this.elements.getTail();
	}

	public CommittableResizableArrayQueue<?> getElements() {
		return this.elements;
	}

	@Override
	public int size() {
		return this.elements.size();
	}

}
