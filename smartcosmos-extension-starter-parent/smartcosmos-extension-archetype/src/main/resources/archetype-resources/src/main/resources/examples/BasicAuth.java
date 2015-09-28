import sun.misc.BASE64Encoder;

public class BasicAuth{
    public static void main(String[] args)
    {
        BASE64Encoder encoder = new BASE64Encoder();
        String authString = "";

        if (args.length == 0 || args.length > 2 )
        {
            System.out.println("Usage:");
            System.out.println("java BasicAuth <password>");
            System.out.println("(assumes username bunkhouse@banzai.com, as in the examples this accompanies)");
            System.out.println("java BasicAuth <username> <password>");
            return;
        }
        if(args.length == 1){
            authString = "bunkhouse@banzai.com:" + args[0];
        }
        if(args.length == 2){
            authString = args[0] + ":" + args[1];
        }
        byte[] authBytes = authString.getBytes();
        System.out.println(encoder.encode(authBytes));
    }
}
