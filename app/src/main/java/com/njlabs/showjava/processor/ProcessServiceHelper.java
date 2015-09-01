package com.njlabs.showjava.processor;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.njlabs.showjava.utils.ExceptionHandler;
import com.njlabs.showjava.utils.logging.Ln;

import java.io.IOException;
import java.io.OutputStream;

@SuppressWarnings("unused")
public class ProcessServiceHelper {

    ProcessService processService;
    Handler UIHandler;
    String packageFilePath;
    String packageName;
    String sourceOutputDir;
    String javaSourceOutputDir;
    ExceptionHandler exceptionHandler;

    public void broadcastStatus(String status) {
        processService.broadcastStatus(status);
    }

    public void broadcastStatus(String statusKey, String statusData) {
        processService.broadcastStatus(statusKey, statusData);
    }

    public void broadcastStatusWithPackageInfo(String statusKey, String dir, String packId) {
        processService.broadcastStatusWithPackageInfo(statusKey, dir, packId);
    }

    protected class ToastRunnable implements Runnable {

        String mText;

        public ToastRunnable(String text) {
            mText = text;
        }

        @Override
        public void run() {
            Toast.makeText(processService.getApplicationContext(), mText, Toast.LENGTH_SHORT).show();
        }
    }

    public class ProgressStream extends OutputStream {
        public ProgressStream() {

        }

        public void write(@NonNull byte[] data, int i1, int i2) {
            String str = new String(data);
            str = str.replace("\n", "").replace("\r", "");
            if (!str.equals("")) {
                broadcastStatus("progress_stream", str);
                Ln.i("DEBUG_INFO" + str);
            }
        }

        @Override
        public void write(int arg0) throws IOException {

        }
    }
}
