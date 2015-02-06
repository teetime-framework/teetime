/**
 * Copyright (C) 2015 TeeTime (http://teetime.sourceforge.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package teetime.framework.pipe;

import teetime.framework.InputPort;
import teetime.framework.OutputPort;
import teetime.framework.signal.ISignal;

/**
 * A pipe implementation used to connect unconnected output ports.
 *
 * @author Christian Wulf
 *
 */
@SuppressWarnings("rawtypes")
public final class DummyPipe implements IPipe {

	public DummyPipe() {}

	@Override
	public boolean add(final Object element) {
		return false;
	}

	@Override
	public Object removeLast() {
		return null;
	}

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public Object readLast() {
		return null;
	}

	@Override
	public InputPort<Object> getTargetPort() {
		return null;
	}

	@Override
	public void sendSignal(final ISignal signal) {}

	@Override
	public void connectPorts(final OutputPort sourcePort, final InputPort targetPort) {}

	@Override
	public void reportNewElement() {
		// do nothing
	}

}
