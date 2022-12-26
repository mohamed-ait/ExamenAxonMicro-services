package org.sid.comptesqrseventsourcing.commonApi.commands;

import lombok.Getter;

public class DeleteCustomerCommand extends BaseCommand<String>{

    public DeleteCustomerCommand(String id) {
        super(id);
    }
}
