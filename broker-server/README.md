# Rabbitmq Docker
### Image: `rabbitmq:3.11-management-alpine`

### Bind ports:
* 5672:5672 (RabbitMQ itself) 
* 15672:15672 (Management UI)

### Bind mounts:

Host file: `/home/volodymyr-pereb/IdeaProjects/amqp-playgrounnd/broker-server/src/main/resources/rabbitmq.conf`

Container file: `/etc/rabbitmq/rabbitmq.conf`