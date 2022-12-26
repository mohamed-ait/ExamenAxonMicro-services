package org.sid.comptesqrseventsourcing.commonApi.commands;

public class DeleteProductCommand extends BaseCommand<String>{

    public DeleteProductCommand(String id) {
        super(id);
    }
}
