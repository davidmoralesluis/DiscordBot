import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.core.spec.MessageCreateSpec;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.Instant;

public class Main {
    public static void main(final String[] args) {
        final String token = "OTU4Njg4MjY4OTY1MjQ5MDk2.YkQ-Bg.mjVndR7skxPFsM7HQVEMF7Jmqhs";
        final DiscordClient client = DiscordClient.create(token);
        final GatewayDiscordClient gateway = client.login().block();



        gateway.on(MessageCreateEvent.class).subscribe(event -> {
            final Message message = event.getMessage();



            if ("!ping".equals(message.getContent())) {
                final MessageChannel channel = message.getChannel().block();
                channel.createMessage("Pong!").block();
            }


            EmbedCreateSpec embed = EmbedCreateSpec.builder()
                    .title("MiAU")
                    //.image("attachment://loki.jpg")
                    .build();


            // -------------- Embed -------------------------

            if ("!embed".equals(message.getContent())) {
                String IMAGE_URL = "https://c.tenor.com/SLzgDKpTvAoAAAAC/game-day.gif";
                String ANY_URL = "https://www.youtube.com/watch?v=0HVI9Zr3FgY";
                final MessageChannel channel = message.getChannel().block();
                EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
                builder.author("DiscordGIF", ANY_URL, IMAGE_URL);
                builder.image(IMAGE_URL);
                builder.title("DiscordGIF");
                builder.url(ANY_URL);
                builder.description("Es un gif multicolor del logo de discord");
                builder.thumbnail(IMAGE_URL);
                builder.footer("GIF", IMAGE_URL);
                builder.timestamp(Instant.now());
                channel.createMessage(builder.build()).block();
            }

        /// ------------- Img ------------------------------------

            if ("!img".equals(message.getContent())) {
                final MessageChannel channel = message.getChannel().block();



                InputStream fileAsInputStream = null;
                try {
                    fileAsInputStream = new FileInputStream("/home/dam1/IdeaProjects/DiscordBot/src/terra.jpg");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                channel.createMessage(MessageCreateSpec.builder()
                        .content("Viva terra")
                        .addFile("/home/dam1/IdeaProjects/DiscordBot/src/terra.jpg", fileAsInputStream)
                        .addEmbed(embed)
                        .build()).subscribe();
            }
        });

        gateway.onDisconnect().block();
    }
}
