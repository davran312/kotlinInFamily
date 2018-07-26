package com.example.virus.kotlininfamily.utils.error_log;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.virus.kotlininfamily.BuildConfig;
import com.example.virus.kotlininfamily.StartApplication;
import com.example.virus.kotlininfamily.utils.DispatchQueue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Locale;

public class FileLog {
    private OutputStreamWriter streamWriter = null;
    private FastDateFormat dateFormat = null;
    private DispatchQueue logQueue = null;
    private File currentFile = null;

    private static volatile FileLog Instance = null;

    public static FileLog getInstance() {
        FileLog localInstance = Instance;
        if (localInstance == null) {
            synchronized (FileLog.class) {
                localInstance = Instance;
                if (localInstance == null) {
                    Instance = localInstance = new FileLog();
                }
            }
        }
        return localInstance;
    }

    public FileLog() {
        dateFormat = FastDateFormat.getInstance("dd_MM_yyyy_HH_mm_ss", Locale.US);
        FastDateFormat folderFormat = FastDateFormat.getInstance("dd_MM_yyyy", Locale.US);
        try {
            File sdCard = StartApplication.INSTANCE.getExternalFilesDir(null);
            if (sdCard == null) {
                return;
            }
            File dir = new File(sdCard.getAbsolutePath() + "/logs");
            dir.mkdirs();
            if (dirSize(dir) >= 5) {
                cleanupLogs();
            }
            currentFile = new File(dir, folderFormat.format(System.currentTimeMillis()) + ".txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            logQueue = new DispatchQueue("logQueue");
            currentFile.createNewFile();
            FileOutputStream stream = new FileOutputStream(currentFile);
            streamWriter = new OutputStreamWriter(stream);
            streamWriter.write("-----start log " + folderFormat.format(System.currentTimeMillis()) + "-----\n");
            streamWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void e(final String message, final Throwable exception) {
        if (BuildConfig.DEBUG) {
            Log.e("___NeoBis___", message, exception);
        }
        if (getInstance().streamWriter != null) {
            getInstance().logQueue.postRunnable(new Runnable() {
                @Override
                public void run() {
                    try {
                        String time = getInstance().dateFormat.format(System.currentTimeMillis());
                        getInstance().streamWriter.write(time + " " + message + "\n");
                        getInstance().streamWriter.write(exception.toString() + "\n\n");
                        getInstance().streamWriter.flush();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void e(final String message) {
        if (BuildConfig.DEBUG) {
            Log.e("___NeoBis___", message);
        }

        if (getInstance().streamWriter != null) {
            getInstance().logQueue.postRunnable(new Runnable() {
                @Override
                public void run() {
                    try {
                        getInstance().streamWriter.write(getInstance().dateFormat
                                .format(System.currentTimeMillis()) + " " + message + "\n\n");
                        getInstance().streamWriter.flush();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void e(final Throwable e) {
        if (BuildConfig.DEBUG) {
            Log.e("___NeoBis___", e.getMessage());
            e.printStackTrace();
        }
        if (getInstance().streamWriter != null) {
            getInstance().logQueue.postRunnable(new Runnable() {
                @Override
                public void run() {
                    try {
                        getInstance().streamWriter.write(getInstance().dateFormat.format(System.currentTimeMillis()) + " " + e + "\n");
                        StackTraceElement[] stack = e.getStackTrace();
                        for (int a = 0; a < stack.length; a++) {
                            getInstance().streamWriter.write(getInstance().dateFormat.format(System.currentTimeMillis()) + " " + stack[a] + "\n");
                        }
                        getInstance().streamWriter.write("\n");
                        getInstance().streamWriter.flush();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            e.printStackTrace();
        }
    }

    public static void d(final String message) {
        if (BuildConfig.DEBUG) {
            Log.e("___NeoBis___", message);
        }
        if (getInstance().streamWriter != null) {
            getInstance().logQueue.postRunnable(new Runnable() {
                @Override
                public void run() {
                    try {
                        getInstance().streamWriter.write(getInstance().dateFormat.format(System.currentTimeMillis()) + " " + message + "\n\n");
                        getInstance().streamWriter.flush();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void w(final String message) {
        if (BuildConfig.DEBUG) {
            Log.e("___NeoBis___", message);
        }
        if (getInstance().streamWriter != null) {
            getInstance().logQueue.postRunnable(new Runnable() {
                @Override
                public void run() {
                    try {
                        getInstance().streamWriter.write(getInstance().dateFormat.format(System.currentTimeMillis()) + " " + message + "\n\n");
                        getInstance().streamWriter.flush();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void cleanupLogs() {
        synchronized (getInstance().currentFile) {
            File sdCard = StartApplication.INSTANCE.getExternalFilesDir(null);
            if (sdCard == null) {
                return;
            }
            File dir = new File(sdCard.getAbsolutePath() + "/logs");
            File[] files = dir.listFiles();
            if (files != null) {
                for (int a = 0; a < files.length; a++) {
                    File file = files[a];
                    if (getInstance().currentFile != null && file.getAbsolutePath().equals(getInstance().currentFile.getAbsolutePath())) {
                        continue;
                    }
                    file.delete();
                }
            }
        }
    }

    /**
     * Return the size of a directory in bytes
     * (5000000 bytes = 5 MB)
     */
    private static long dirSize(File dir) {

        if (dir.exists()) {
            long result = 0;
            File[] fileList = dir.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                // Recursive call if it's a directory
                if (fileList[i].isDirectory()) {
                    result += dirSize(fileList[i]);
                } else {
                    // Sum the file size in bytes
                    result += fileList[i].length();
                }
            }
            return result / 1000000; // return the file size in MB
        }
        return 0;
    }

    public static void showError(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
