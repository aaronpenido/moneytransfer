import com.google.inject.Binder;
import com.google.inject.Module;
import controllers.TransferController;

public class RevolutTransferModule implements Module {

    public void configure(Binder binder) {
        binder.bind(TransferController.class);
    }
}
