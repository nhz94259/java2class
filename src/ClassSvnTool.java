import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by wolf
 */
public class ClassSvnTool {
    ClassSvnTool( ) {

    }
    ClassSvnTool(String target,String clazz) {
        this.clazz=clazz;
        this.target=target;
    }
    static   int    count ;

    private  String target  ;
    private  String clazz  ;
    StringBuilder stringBuilder = new StringBuilder();



    Boolean delete =false;
    private  String RESULT  ;

    private static String target_Path ;
    private static String class_Path ;


    /*
     * 根据java文件找到对应的class文件，并将lclass文件复制到对应目录下，同时删除java文件
     */
    public   void createPath(File file) {

        if (file.exists()) {
            File[] files = file.listFiles();
            for (File f : files) {
                if (f.isDirectory()) {// 判断是否是目录
                     createPath(f);
                } else {
                    if (f.getName().endsWith(".java")) {
                        // 去掉扩展名之后的文件名
                        final String temp  ;
                        if(f.getName().contains("java")){
                            temp = f.getName().substring(0, f.getName().indexOf(".java"));
                        }else if (f.getName().contains("py")){
                            temp = f.getName().substring(0, f.getName().indexOf(".py"));
                        }else if (f.getName().contains("xml")){
                            temp = f.getName().substring(0, f.getName().indexOf(".xml"));
                        }else if (f.getName().contains("property")){
                            temp = f.getName().substring(0, f.getName().indexOf(".property"));
                        }else {
                            temp = f.getName().substring(0, f.getName().indexOf( f.getName().split(".")[1] ));
                        }

                        // 编译所在文件夹
                        final String path = class_Path
                                + f.getPath().substring(target_Path.length(), f.getPath().lastIndexOf("\\") + 1);
                        File[] filels = new File(path).listFiles();// 获取文件夹下的所有文件
                        for (int i = 0; i < filels.length; i++) {
                            if (filels[i].isFile()) {
                                if (filels[i].getName().indexOf(temp) != -1)// 将带$的class也copy出来
                                {
                                    count++;
                                    File classfile = new File(path + filels[i].getName());
                                    File javafile = new File(f.getPath().substring(0, f.getPath().lastIndexOf("\\") + 1)
                                            + filels[i].getName());
                                    try {
                                        copyFile(classfile, javafile);
                                         if(delete){
                                             //System.out.println("删除源文件");
                                             f.delete();
                                         }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                   // System.out.println(filels[i].getName());
                                    stringBuilder.append(classfile).append("\n");
                                }
                            }
                        }
                    }
                }
            }
        }
         setRESULT(stringBuilder.toString());
    }

    /**
     * 将F1复制到F2
     *
     */
    public static void copyFile(File f1, File f2) throws Exception {
        int length = 2097152;
        FileInputStream in = new FileInputStream(f1);
        FileOutputStream out = new FileOutputStream(f2);
        byte[] buffer = new byte[length];

        int len = 0;
        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        in.close();
        out.flush();
        out.close();
    }

    public  void setClass_Path(String class_Path) {
        this.class_Path = class_Path;
    }
    public   void setTarget_Path(String target_Path) {
        this.target_Path = target_Path;
    }
    public   int getCount() {
        return count;
    }
    public   void initCount( ) {
        this.count = 0;
        setRESULT("");
    }
    public String getTarget() {
        return target;
    }

    public String getClazz() {
        return clazz;
    }

    public String getRESULT() {
        return RESULT;
    }

    public void setRESULT(String RESULT) {
        this.RESULT = RESULT;
    }

    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }
}

