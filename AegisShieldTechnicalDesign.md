**Technical Design**

# Introduction #

This is the technical design page for the Aegis Shield application


# Application packaging #
For the moment I don't have an idea of what is the best way we should organize the packages of the application, but I did some thinking and the original way of packaging by type of code (e.g. .activiy, .service, .content) doesn't seem like a good idea because it segregates code that serves the same functionality. In this light, I thought that by adding all code that builds a functionality (activities, services, intent receivers) in the same package (e.g. addaccount, .password) would be a better way we can organize the code.

# Basic Design #
For each screen/action we should have a separate layout xml file. The main layout should be reserved for the main application activity, that will be the access point to all other screens.