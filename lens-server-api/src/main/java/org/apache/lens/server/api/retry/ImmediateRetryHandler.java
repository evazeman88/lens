/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.lens.server.api.retry;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ImmediateRetryHandler<FC extends FailureContext> implements BackOffRetryHandler<FC> {

  /**
   * The Constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  private final int retries;

  // default 1 retry
  public ImmediateRetryHandler() {
    this(1);
  }

  public ImmediateRetryHandler(String retries) {
    this(Integer.parseInt(retries));
  }

  @Override
  public boolean canTryOpNow(FC failContext) {
    return true;
  }

  @Override
  public long getOperationNextTime(FC failContext) {
    return System.currentTimeMillis();
  }

  @Override
  public boolean hasExhaustedRetries(FC failContext) {
    return failContext.getFailCount() > retries;
  }
}
