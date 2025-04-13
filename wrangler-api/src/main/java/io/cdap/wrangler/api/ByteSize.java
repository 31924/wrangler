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

package io.cdap.wrangler.api.parser;

public class ByteSize extends Token {
  private final long bytes;

  public ByteSize(String value) {
    super(value);
    this.bytes = parseBytes(value);
  }

  private long parseBytes(String value) {
    value = value.trim().toUpperCase();
    if (value.endsWith("KB")) {
      return (long) (Double.parseDouble(value.replace("KB", "")) * 1024);
    } else if (value.endsWith("MB")) {
      return (long) (Double.parseDouble(value.replace("MB", "")) * 1024 * 1024);
    } else if (value.endsWith("GB")) {
      return (long) (Double.parseDouble(value.replace("GB", "")) * 1024 * 1024 * 1024);
    }
    return Long.parseLong(value);
  }

  public long getBytes() {
    return bytes;
  }
}
