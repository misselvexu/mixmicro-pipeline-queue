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
package com.jd.joyqueue.broker.kafka.config;

import com.jd.joyqueue.domain.QosLevel;
import com.jd.joyqueue.toolkit.config.PropertyDef;
import com.jd.joyqueue.toolkit.config.PropertySupplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * KafkaConfig
 * author: gaohaoxiang
 * email: gaohaoxiang@jd.com
 * date: 2018/11/5
 */
public class KafkaConfig {
    protected static final Logger logger = LoggerFactory.getLogger(KafkaConfig.class);

    private PropertySupplier propertySupplier;

    public KafkaConfig(PropertySupplier propertySupplier) {
        this.propertySupplier = propertySupplier;
    }

    public int getFetchBatchSize() {
        return getConfig(KafkaConfigKey.FETCH_BATCH_SIZE);
    }

    public int getMetadataDelay() {
        return getConfig(KafkaConfigKey.METADATA_DELAY);
    }

    public int getOffsetSyncTimeout() {
        return getConfig(KafkaConfigKey.OFFSET_SYNC_TIMEOUT);
    }

    public int getOffsetCacheExpireTime() {
        return getConfig(KafkaConfigKey.OFFSET_CACHE_EXPIRE_TIME);
    }

    public int getTransactionSyncTimeout() {
        return getConfig(KafkaConfigKey.TRANSACTION_SYNC_TIMEOUT);
    }

    public int getTransactionTimeout() {
        return getConfig(KafkaConfigKey.TRANSACTION_TIMEOUT);
    }

    public int getTransactionLogWriteTimeout() {
        return getConfig(KafkaConfigKey.TRANSACTION_LOG_WRITE_TIMEOUT);
    }

    public int getTransactionLogRetries() {
        return getConfig(KafkaConfigKey.TRANSACTION_LOG_RETRIES);
    }

    public int getTransactionLogInterval() {
        return getConfig(KafkaConfigKey.TRANSACTION_LOG_INTERVAL);
    }

    public int getTransactionProducerSequenceExpire() {
        return getConfig(KafkaConfigKey.TRANSACTION_PRODUCER_SEQUENCE_EXPIRE);
    }

    public String getTransactionLogApp() {
        return getConfig(KafkaConfigKey.TRANSACTION_LOG_APP);
    }

    public int getTransactionLogScanSize() {
        return getConfig(KafkaConfigKey.TRANSACTION_LOG_SCAN_SIZE);
    }

    public QosLevel getTransactionLogWriteQosLevel() {
        return QosLevel.valueOf((int) getConfig(KafkaConfigKey.TRANSACTION_LOG_WRITE_QOSLEVEL));
    }

    public int getSessionMaxTimeout() {
        return getConfig(KafkaConfigKey.SESSION_MIN_TIMEOUT);
    }

    public int getSessionMinTimeout() {
        return getConfig(KafkaConfigKey.SESSION_MAX_TIMEOUT);
    }

    public int getPermitsPerSecond() {
        return getConfig(KafkaConfigKey.PERMITSPER_SECOND);
    }

    public int getAcquirePermitsTimeout() {
        return getConfig(KafkaConfigKey.ACQUIRE_PERMITS_TIMEOUT);
    }

    public int getRebalanceInitialDelay() {
        return getConfig(KafkaConfigKey.REBALANCE_INITIAL_DELAY);
    }

    public int getRebalanceTimeout() {
        return getConfig(KafkaConfigKey.REBALANCE_TIMEOUT);
    }

    public int getRebalanceHeartbeatOverflow() {
        return getConfig(KafkaConfigKey.REBALANCE_HEARTBEAT_OVERFLOW);
    }

    protected <T> T getConfig(String key, PropertyDef.Type type, Object defaultValue) {
        return PropertySupplier.getValue(this.propertySupplier, key, type, defaultValue);
    }

    protected <T> T getConfig(PropertyDef key) {
        return PropertySupplier.getValue(this.propertySupplier, key);
    }
}