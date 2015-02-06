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
package teetime.stage.util;

import java.io.File;

/**
 * @author Christian Wulf
 *
 * @since 1.10
 */
public class TextLine {

	private final File textFile;
	private final String textLine;

	/**
	 * @since 1.10
	 */
	public TextLine(final File textFile, final String textLine) {
		this.textFile = textFile;
		this.textLine = textLine;
	}

	public File getTextFile() {
		return this.textFile;
	}

	public String getTextLine() {
		return this.textLine;
	}
}
