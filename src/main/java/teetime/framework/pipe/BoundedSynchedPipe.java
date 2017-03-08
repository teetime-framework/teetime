/**
 * Copyright © 2015 Christian Wulf, Nelson Tavares de Sousa (http://teetime-framework.github.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package teetime.framework.pipe;

import teetime.framework.*;
import teetime.framework.pipe.strategy.SleepIfFullStrategy;
import teetime.util.framework.concurrent.queue.ObservableSpScArrayQueue;

public class BoundedSynchedPipe<T> extends AbstractSynchedPipe<T> implements IMonitorablePipe {

	private final ObservableSpScArrayQueue<Object> queue;
	private final SleepIfFullStrategy strategy;

	public BoundedSynchedPipe(final OutputPort<? extends T> sourcePort, final InputPort<T> targetPort, final int capacity) {
		super(sourcePort, targetPort);
		queue = new ObservableSpScArrayQueue<Object>(capacity);
		strategy = new SleepIfFullStrategy();
	}

	@Override
	public boolean add(final Object element) {
		return strategy.add(this, element);
	}

	@Override
	public boolean addNonBlocking(final Object element) {
		return this.queue.offer(element);
	}

	@Override
	public Object removeLast() {
		return this.queue.poll();
	}

	@Override
	public boolean isEmpty() {
		return this.queue.isEmpty();
	}

	@Override
	public int size() {
		return this.queue.size();
	}

	@Override
	public int getNumWaits() {
		return strategy.getNumWaits();
	}

	@Override
	public long getPushThroughput() {
		return queue.getNumPushes();
	}

	@Override
	public long getPullThroughput() {
		return queue.getNumPulls();
	}

	@Override
	public long getNumPushes() {
		return queue.getNumPushesSinceAppStart();
	}

	@Override
	public long getNumPulls() {
		return queue.getNumPullsSinceAppStart();
	}

	@Override
	public int capacity() {
		return this.queue.capacity();
	}

}
