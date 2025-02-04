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
package io.hyscale.commons.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.hyscale.commons.config.SetupConfig;
import io.hyscale.commons.constants.ToolConstants;

/**
 * Utility class to provide windows os specific changes
 * like file separator among others
 * @author tushart
 *
 */
public class WindowsUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(WindowsUtil.class);
	public static final String HYSCALE_HOST_FS = System.getenv(ToolConstants.HYSCALE_HOST_FS_PROPERTY);
	private static final String WINDOWS_FS_MATCHER = "\\\\";

	/**
	 * 
	 * @return true if HYSCALE_HOST_FS property is that of windows
	 */
	public static boolean isHostWindows() {
		if (WINDOWS_FS_MATCHER.equals(HYSCALE_HOST_FS)) {
			logger.debug("The Host operating system is windows");
			return true;
		}
		return false;
	}

	/**
	 * Update file separator to host file separator if the host is windows
	 * 
	 */
	public static String updateToHostFileSeparator(String filepath) {
		if (StringUtils.isBlank(filepath)) {
			return filepath;
		}
		if (!isHostWindows()) {
			return filepath;
		}
		return filepath.replaceAll(SetupConfig.FILE_SEPARATOR, WINDOWS_FS_MATCHER);
	}
	
	/**
     * Replace windows file separator(\) with unix file separator(/)
     * @param fileSeperator
     * @return unix file separator based file path
     */
    public static String updateToUnixFileSeparator(String filePath) {
    	if (StringUtils.isBlank(filePath)) {
			return filePath;
		}
		return filePath.replaceAll(WINDOWS_FS_MATCHER, ToolConstants.LINUX_FILE_SEPARATOR);
    }
}
