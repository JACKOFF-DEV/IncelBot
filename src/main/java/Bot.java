import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Bot {

    public static void main(String[] args) throws Exception{

        //Scanner scan = new Scanner(System.in);

        //weener
        chooseMessage();

        JDA jda = JDABuilder.createDefault("NzUwMTk1MTk2OTQ3NTk1MzU1.X02_uA.RqazCazU993B0kWo8K1B4SBcHCI").build();

        Random rand = new Random();
        while (true) {
            chooseMessage();
            //864000000
            int sleepTime = rand.nextInt(100000000);
            System.out.println(sleepTime);
            //String go = scan.next();
            //if (go != null) {
                Thread.sleep(sleepTime);
                List<TextChannel> channels = jda.getTextChannelsByName("incel-talk", true);
                for (TextChannel ch : channels) {
                    sendMessage(ch, chooseMessage());
                }
            //}
        }
    }

    private static String chooseMessage() throws IOException {
        Document document = Jsoup.connect("https://incels.is/forums/inceldom-discussion.2/").get();
        Elements withAttr = new Elements();
        for( Element element : document.getAllElements() )
        {
            for( Attribute attribute : element.attributes() )
            {
                if( attribute.getValue().equalsIgnoreCase("preview-tooltip") )
                {
                    withAttr.add(element);
                }
            }
        }
        int indexAmnt = withAttr.size();
        Random rand = new Random();
        int index = (rand.nextInt(indexAmnt-3))+3;
        return (withAttr.eq(index).text());
    }
    private static void sendMessage(TextChannel ch, String msg) {
        ch.sendMessage(msg).queue();
    }

}
