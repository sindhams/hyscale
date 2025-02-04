/**
 * Copyright 2019 Pramati Prism, Inc.
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
package io.hyscale.dockerfile.gen.services.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.hyscale.commons.config.SetupConfig;

@Component
public class DockerfileGenConfig {

	private static final String DOCKERFILE = "Dockerfile";
	private static final String DOCKERFILE_DIR = "dockerfiles";
	private static final String ARTIFACT_DIR = "artifacts";
	private static final String SCRIPTS_DIR = "init_scripts";
	private static final String HYSCALE = "hyscale";
	private static final String SHELL_START_SCRIPT = "/bin/sh";

	public static final String CONFIGURE_SCRIPT = "configure.sh";
	public static final String RUN_SCRIPT = "run.sh";

	@Autowired
	private SetupConfig setupConfig;

	public String getDockerFileDir(String appName, String serviceName) {
		StringBuilder dir = new StringBuilder(getDockerFileParentDir(appName, serviceName));
		dir.append(DOCKERFILE);
		return dir.toString();
	}

	public String getDockerFileParentDir(String appName, String serviceName) {
		StringBuilder dir = new StringBuilder(setupConfig.getGeneratedFilesDir(appName, serviceName));
		dir.append(DOCKERFILE_DIR).append(SetupConfig.FILE_SEPARATOR);
		return dir.toString();
	}

	public String getRelativeArtifactDir(String artifactName) {
		StringBuilder dir = new StringBuilder(ARTIFACT_DIR);
		dir.append(SetupConfig.FILE_SEPARATOR).append(artifactName).append(SetupConfig.FILE_SEPARATOR);
		return dir.toString();
	}

	public String getScriptDestinationDir() {
		StringBuilder dir = new StringBuilder();
		dir.append(SetupConfig.FILE_SEPARATOR);
		dir.append(HYSCALE).append(SetupConfig.FILE_SEPARATOR).append(SCRIPTS_DIR).append(SetupConfig.FILE_SEPARATOR);
		return dir.toString();
	}

	public String getRunScriptAbsoluteDir(String appName, String serviceName) {
		StringBuilder dir = new StringBuilder(setupConfig.getServiceDir(appName, serviceName));
		dir.append(DOCKERFILE_DIR).append(SetupConfig.FILE_SEPARATOR).append(RUN_SCRIPT);
		return dir.toString();
	}

	public String getConfigureScriptAbsoluteDir(String appName, String serviceName) {
		StringBuilder dir = new StringBuilder(setupConfig.getServiceDir(appName, serviceName));
		dir.append(DOCKERFILE_DIR).append(SetupConfig.FILE_SEPARATOR).append(CONFIGURE_SCRIPT);
		return dir.toString();
	}

	public String getConfigureScriptContainerDir() {
		StringBuilder dir = new StringBuilder(getScriptDestinationDir());
		dir.append(CONFIGURE_SCRIPT);
		return dir.toString();
	}

	public String getRunScriptContainerDir() {
		StringBuilder dir = new StringBuilder(getScriptDestinationDir());
		dir.append(RUN_SCRIPT);
		return dir.toString();
	}

	public String getShellStartScript() {
		return SHELL_START_SCRIPT;
	}

}