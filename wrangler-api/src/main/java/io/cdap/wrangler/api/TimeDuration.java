/*
 * Copyright © 2017-2019 Cask Data, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */


package io.cdap.wrangler.api;

public class TimeDuration {
    private long milliseconds;

    public TimeDuration(String value) {
        value = value.toLowerCase();
        if (value.endsWith("ms")) {
            milliseconds = Long.parseLong(value.replace("ms", ""));
        } else if (value.endsWith("s")) {
            milliseconds = Long.parseLong(value.replace("s", "")) * 1000;
        } else if (value.endsWith("m")) {
            milliseconds = Long.parseLong(value.replace("m", "")) * 60 * 1000;
        } else if (value.endsWith("h")) {
            milliseconds = Long.parseLong(value.replace("h", "")) * 60 * 60 * 1000;
        } else if (value.endsWith("d")) {
            milliseconds = Long.parseLong(value.replace("d", "")) * 24 * 60 * 60 * 1000;
        } else {
            throw new IllegalArgumentException("Invalid time duration format: " + value);
        }
    }

    public long getMilliseconds() {
        return milliseconds;
    }
}
