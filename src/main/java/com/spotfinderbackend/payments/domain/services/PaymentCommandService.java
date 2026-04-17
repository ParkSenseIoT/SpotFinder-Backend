package com.spotfinderbackend.payments.domain.services;

import com.spotfinderbackend.payments.domain.model.commands.CreatePaymentCommand;
import com.spotfinderbackend.payments.domain.model.commands.ProcessCulqiPaymentCommand;
import com.spotfinderbackend.payments.domain.model.commands.ProcessYapePaymentCommand;

public interface PaymentCommandService {

    Long handle(CreatePaymentCommand command);

    void handle(ProcessCulqiPaymentCommand command);

    void handle(ProcessYapePaymentCommand command);
}