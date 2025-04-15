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

public class ByteSize {
    private long bytes;

    public ByteSize(String value) {
        value = value.toUpperCase();
        if (value.endsWith("KB")) {
            bytes = Long.parseLong(value.replace("KB", "")) * 1024;
        } else if (value.endsWith("MB")) {
            bytes = Long.parseLong(value.replace("MB", "")) * 1024 * 1024;
        } else if (value.endsWith("GB")) {
            bytes = Long.parseLong(value.replace("GB", "")) * 1024 * 1024 * 1024;
        } else if (value.endsWith("TB")) {
            bytes = Long.parseLong(value.replace("TB", "")) * 1024L * 1024 * 1024 * 1024;
        } else if (value.endsWith("B")) {
            bytes = Long.parseLong(value.replace("B", ""));
        } else {
            throw new IllegalArgumentException("Invalid byte size format: " + value);
        }
    }

    public long getBytes() {
        return bytes;
    }
}

  }
}
