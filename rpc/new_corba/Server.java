package schedule;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManager;

/**
 * Server.java
 *
 * @author <a href="mailto:torutk@alles.or.jp">Toru TAKAHASHI</a>
 * @version
 */

public class Server {
    public static void main(String[] args) {

        ORB orb = ORB.init(args, null);

        // RootPOAの取得
        org.omg.CORBA.Object objPoa = null;
        POA rootPoa = null;
        try {
            objPoa = orb.resolve_initial_references("RootPOA");
            rootPoa = POAHelper.narrow(objPoa);
        } catch (org.omg.CORBA.ORBPackage.InvalidName e) {
            System.out.println("RootPOAの名前が正しくありません");
            System.exit(1);
        }

        if (rootPoa == null) {
            System.out.println("POAのオブジェクト参照が取得できません");
            System.exit(1);
        }

        // Servantの作成
        SimpleScheduler scheduler = new SimpleScheduler();
        org.omg.CORBA.Object schedulerRef = null;
        try {
            rootPoa.activate_object(scheduler);
            schedulerRef = rootPoa.servant_to_reference(scheduler);
        } catch (Exception e) {
            System.out.println("Schedulerオブジェクト作成でエラー");
            e.printStackTrace();
            System.exit(1);
        }
        
        // NamingServiceへの登録
        // ルートネーミングコンテクストオブジェクトの取得
        org.omg.CORBA.Object objNs = null;
        try {
            objNs = orb.resolve_initial_references("NameService");
        } catch (org.omg.CORBA.ORBPackage.InvalidName e) {
            System.out.println("ネーミングサービスの名前が正しくありません");
            e.printStackTrace();
            System.exit(1);
        }
        NamingContext rootNameContext = NamingContextHelper.narrow(objNs);
        if (rootNameContext == null) {
            System.out.println("ルートネーミングコンテクストが空です");
            System.exit(1);
        }
        
        // Schedulerオブジェクトのネームコンポーネント作成
        NameComponent[] schedulerNameComponent = new NameComponent[1];

        schedulerNameComponent[0] = new NameComponent("TheScheduler", "Object");

        try {
            rootNameContext.rebind(schedulerNameComponent, schedulerRef);
            System.out.println("Registered scheduler object to name service");
        } catch (Exception e) {
            System.out.println("Schedulerオブジェクトのネーミングサービスへの登録で
　　　　　　　　　　　　　　　　　エラーが発生しました");
            e.printStackTrace();
            System.exit(1);
        }

        // POAマネージャの活性化
        POAManager poaManager = rootPoa.the_POAManager();
        try {
            poaManager.activate();
            System.out.println("activated POA Manager");
        } catch (org.omg.PortableServer.POAManagerPackage.AdapterInactive e) {
            System.out.println("POAマネージャの活性化に失敗しました");
            e.printStackTrace();
            System.exit(1);
        }

        // クライアントからの要求の待ち合わせ
        System.out.println("Waiting request from client");
        try {
            orb.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} // Server
