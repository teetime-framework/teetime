/**
 * Copyright (C) 2015 Christian Wulf, Nelson Tavares de Sousa (http://christianwulf.github.io/teetime)
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
package teetime.stage;

import java.util.Arrays;

import teetime.framework.AbstractProducerStage;

public class InitialElementProducer<T> extends AbstractProducerStage<T> {

	private Iterable<T> elements;

	public InitialElementProducer(final T... elements) {
		this.elements = Arrays.asList(elements);
	}

	public <O extends Iterable<T>> InitialElementProducer(final O elements) {
		this.elements = elements;
	}

	@Override
	public void onStarting() throws Exception {
		if (elements == null) {
			throw new IllegalArgumentException("The given iterable must not be null");
		}
		super.onStarting();
	}

	@Override
	protected void execute() {
		for (final T element : this.elements) {
			this.outputPort.send(element);
		}
		this.terminate();
	}

	public void setIter(final T... elements) {
		this.elements = Arrays.asList(elements);
	}

	public void setIter(final Iterable<T> elements) {
		this.elements = elements;
	}

}
