package net.smartcosmos.pojo.batch;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Core SDK
 * ===============================================================================
 * Copyright (C) 2013-2015 SMARTRAC Technology Fletcher, Inc.
 * ===============================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#
 */

import com.fasterxml.jackson.annotation.JsonView;
import net.smartcosmos.model.batch.BatchProcessorStatus;
import net.smartcosmos.model.batch.IBatchStatusReport;
import net.smartcosmos.util.json.JsonGenerationView;

public class BatchStatusReport implements IBatchStatusReport
{
    @JsonView(JsonGenerationView.Full.class)
    private long batchProcessorStartTimestamp;

    @JsonView(JsonGenerationView.Minimum.class)
    private BatchProcessorStatus batchProcessorStatus;

    @JsonView(JsonGenerationView.Standard.class)
    private int percentageComplete;

    @JsonView(JsonGenerationView.Full.class)
    private long lastPercentageCompleteUpdateTimestamp;

    @JsonView(JsonGenerationView.Full.class)
    private long batchProcessorStopTimestamp;

    @JsonView(JsonGenerationView.Minimum.class)
    private String errorMessage;

    @JsonView(JsonGenerationView.Minimum.class)
    private int errorCode;

    @Override
    public long getBatchProcessorStartTimestamp()
    {
        return batchProcessorStartTimestamp;
    }

    @Override
    public BatchProcessorStatus getBatchProcessorStatus()
    {
        return batchProcessorStatus;
    }

    @Override
    public int getPercentageComplete()
    {
        return percentageComplete;
    }

    @Override
    public long getLastPercentageCompleteUpdateTimestamp()
    {
        return lastPercentageCompleteUpdateTimestamp;
    }

    @Override
    public long getBatchProcessorStopTimestamp()
    {
        return batchProcessorStopTimestamp;
    }

    @Override
    public String getErrorMessage()
    {
        return errorMessage;
    }

    @Override
    public int getErrorCode()
    {
        return errorCode;
    }

    @Override
    public void setBatchProcessorStartTimestamp(long batchProcessorStartTimestamp)
    {
        this.batchProcessorStartTimestamp = batchProcessorStartTimestamp;
    }

    @Override
    public void setBatchProcessorStatus(BatchProcessorStatus batchProcessorStatus)
    {
        this.batchProcessorStatus = batchProcessorStatus;
    }

    @Override
    public void setPercentageComplete(int percentageComplete)
    {
        this.percentageComplete = percentageComplete;
    }

    @Override
    public void setLastPercentageCompleteUpdateTimestamp(long lastPercentageCompleteUpdateTimestamp)
    {
        this.lastPercentageCompleteUpdateTimestamp = lastPercentageCompleteUpdateTimestamp;
    }

    @Override
    public void setBatchProcessorStopTimestamp(long batchProcessorStopTimestamp)
    {
        this.batchProcessorStopTimestamp = batchProcessorStopTimestamp;
    }

    @Override
    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    @Override
    public void setErrorCode(int errorCode)
    {
        this.errorCode = errorCode;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BatchStatusReport that = (BatchStatusReport) o;

        if (batchProcessorStartTimestamp != that.batchProcessorStartTimestamp) return false;
        if (batchProcessorStopTimestamp != that.batchProcessorStopTimestamp) return false;
        if (errorCode != that.errorCode) return false;
        if (lastPercentageCompleteUpdateTimestamp != that.lastPercentageCompleteUpdateTimestamp) return false;
        if (percentageComplete != that.percentageComplete) return false;
        if (batchProcessorStatus != that.batchProcessorStatus) return false;
        if (errorMessage != null ? !errorMessage.equals(that.errorMessage) : that.errorMessage != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int) (batchProcessorStartTimestamp ^ (batchProcessorStartTimestamp >>> 32));
        result = 31 * result + batchProcessorStatus.hashCode();
        result = 31 * result + percentageComplete;
        result = 31 * result +
                (int) (lastPercentageCompleteUpdateTimestamp ^ (lastPercentageCompleteUpdateTimestamp >>> 32));
        result = 31 * result + (int) (batchProcessorStopTimestamp ^ (batchProcessorStopTimestamp >>> 32));
        result = 31 * result + (errorMessage != null ? errorMessage.hashCode() : 0);
        result = 31 * result + errorCode;
        return result;
    }
}
