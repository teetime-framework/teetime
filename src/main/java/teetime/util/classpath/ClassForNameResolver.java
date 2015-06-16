/**
 * Copyright (C) 2015 Christian Wulf, Nelson Tavares de Sousa (http://teetime.sourceforge.net)
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
package teetime.util.classpath;

/**
 * @param <T>
 *            the type that is used to cast a type that was found in the class path
 *
 * @author Christian Wulf
 * @since 1.11
 */
public final class ClassForNameResolver<T> {

	private final Class<T> classToCast;

	public ClassForNameResolver(final Class<T> classToCast) {
		this.classToCast = classToCast;
	}

	/**
	 * This method tries to find a class with the given name.
	 *
	 * @param classname
	 *            The name of the class.
	 *
	 * @return A {@link Class} instance corresponding to the given name, if it exists.
	 * @throws ClassNotFoundException
	 *             thrown iff no class was found for the given <b>classname</b>
	 *
	 */
	public final Class<? extends T> classForName(final String classname) throws ClassNotFoundException {
		final Class<?> clazz = Class.forName(classname);
		return clazz.asSubclass(this.classToCast);
	}
}
