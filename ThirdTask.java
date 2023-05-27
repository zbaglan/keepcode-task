    void processTask(ChannelHandlerContext ctx) {
        InetSocketAddress lineAddress = new InetSocketAddress(getIpAddress(), getUdpPort());
        CommandType typeToRemove;
        for (Command currentCommand : getAllCommands()) {

            if (currentCommand.isAttemptsNumberExhausted()) {
                typeToRemove = currentCommand.getCommandType();
                deleteCommand(typeToRemove);
                continue;
            }

            if (currentCommand.getCommandType() != CommandType.REBOOT_CHANNEL || currentCommand.isTimeToSend()) {
                sendCommand(ctx, lineAddress, currentCommand);
            }
        }
        sendKeepAliveOkAndFlush(ctx);
    }

    private void sendCommand(ChannelHandlerContext ctx, InetSocketAddress lineAddress, Command currentCommand) {
        sendCommandToContext(ctx, lineAddress, currentCommand.getCommandText());

        try {
            AdminController.getInstance().processUssdMessage(
                    new DblIncomeUssdMessage(lineAddress.getHostName(), lineAddress.getPort(), 0, EnumGoip.getByModel(getGoipModel()), currentCommand.getCommandText()), false);
        } catch (Exception ignored) {
        }
        Log.ussd.write(String.format("sent: ip: %s; порт: %d; %s",
                lineAddress.getHostString(), lineAddress.getPort(), currentCommand.getCommandText()));
        currentCommand.setSendDate(new Date());
        currentCommand.incSendCounter();
    }