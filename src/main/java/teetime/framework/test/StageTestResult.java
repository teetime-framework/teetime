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
package teetime.framework.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import teetime.framework.OutputPort;

public class StageTestResult {

	private final Map<OutputPort<?>, List<?>> elementsPerPort = new HashMap<>();

	public <T> void add(final OutputPort<T> port, final List<T> outputElements) {
		elementsPerPort.put(port, outputElements);
	}

	@SuppressWarnings("unchecked")
	public <T> List<? extends T> getElementsFrom(final OutputPort<T> outputPort) {
		return (List<T>) elementsPerPort.get(outputPort);
	}

}
