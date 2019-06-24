/**
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
package com.jd.joyqueue.broker.limit.support;

import com.google.common.collect.Sets;
import com.jd.joyqueue.broker.limit.LimitRejectedStrategy;
import com.jd.joyqueue.broker.limit.domain.LimitContext;
import com.jd.joyqueue.network.transport.command.Command;
import com.jd.joyqueue.toolkit.delay.AbstractDelayedOperation;
import com.jd.joyqueue.toolkit.delay.DelayedOperationKey;
import com.jd.joyqueue.toolkit.delay.DelayedOperationManager;

/**
 * DelayLimitRejectedStrategy
 * author: gaohaoxiang
 * email: gaohaoxiang@jd.com
 * date: 2019/5/16
 */
public class DelayLimitRejectedStrategy implements LimitRejectedStrategy {

    private DelayedOperationManager delayPurgatory;

    public DelayLimitRejectedStrategy() {
        this.delayPurgatory = new DelayedOperationManager("joyqueue-limit-delayed");
        this.delayPurgatory.start();
    }

    @Override
    public Command execute(LimitContext context) {
        delayPurgatory.tryCompleteElseWatch(new AbstractDelayedOperation(context.getDelay()) {
            @Override
            protected void onComplete() {
                context.getTransport().acknowledge(context.getRequest(), context.getResponse());
            }
        }, Sets.newHashSet(new DelayedOperationKey()));
        return null;
    }

    @Override
    public String type() {
        return "delay";
    }
}