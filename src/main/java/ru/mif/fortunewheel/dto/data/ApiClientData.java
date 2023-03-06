package ru.mif.fortunewheel.dto.data;

import ru.mif.fortunewheel.domain.APIClient;
import ru.mif.fortunewheel.dto.Data;

public class ApiClientData extends Data<APIClient> {

    private final String domain;
    private final String agent;

    public ApiClientData(APIClient apiClient) {
        super(apiClient);
        this.domain = apiClient.getDomain();
        this.agent = apiClient.getAgent();
    }

    public String getDomain() {
        return domain;
    }

    public String getAgent() {
        return agent;
    }
}
