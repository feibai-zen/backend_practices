package com.feibai.study.spring.ioc.beans;

public class ClientService {

    private static ClientService clientService = new ClientService();

    private ClientService() {
    }

    public static ClientService createInstance() {
        return clientService;
    }
}
