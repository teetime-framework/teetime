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
package teetime.framework;

import teetime.util.framework.concurrent.SignalingCounter;

public class DynamicActuator {

	/**
	 * @deprecated Use {@link #startWithinNewThread(Stage)} instead.
	 */
	@Deprecated
	public AbstractRunnableStage wrap(final Stage stage) {
		if (stage.getInputPorts().size() > 0) {
			return new RunnableConsumerStage(stage);
		}
		return new RunnableProducerStage(stage);
	}

	public Runnable startWithinNewThread(final Stage previousStage, final Stage stage) {
		SignalingCounter runtimeCounter = previousStage.getOwningContext().getThreadService().getRunnableCounter();
		SignalingCounter newCounter = stage.getOwningContext().getThreadService().getRunnableCounter();
		// runtimeCounter.inc(newCounter);

		// stage.logger.error(stage.owningContext.getThreadService().getRunnableCounter().toString());

		// !!! stage.owningContext = XXX.owningContext !!!

		Runnable runnable = wrap(stage);
		Thread thread = new Thread(runnable);

		stage.setOwningThread(thread);
		stage.setExceptionHandler(null);

		thread.start();

		// requirements:
		// 1. all new threads from stage must be known to the global context
		// 2. number of active threads must be increased by the stage

		if (runnable instanceof RunnableConsumerStage) {
			// do nothing
		} else if (runnable instanceof RunnableProducerStage) {
			((RunnableProducerStage) runnable).triggerInitializingSignal();
			((RunnableProducerStage) runnable).triggerStartingSignal();
		} else {
			// TODO
		}

		return runnable;
	}
}
