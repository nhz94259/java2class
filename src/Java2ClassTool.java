import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by wolf
 */
public class Java2ClassTool extends JFrame {
    /**
     * 声明
     */
    private JPanel contentPane;

    private JTextField textField;

    private  JTextField jTextField_UP_1;

    private  JTextField jTextField_UP_2;

    private  JTextField Jtailf1;
    private  JTextField Jtailf2;


    private  JLabel jLabe_AUTHOR;

    private  JLabel jLabe_DESCRIPTION;

    private JScrollPane scrollPane_1;

    private JComboBox jComboBox;
    String ClassCopyResult;//拷贝结果

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        /**
         * star threa
         */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Java2ClassTool frame = new Java2ClassTool();
                    frame.setVisible(true);//设置可见
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Java2ClassTool() {

        {

        }
        setTitle("class打包工具");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 50, 800, 650);

        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.controlHighlight);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        jLabe_AUTHOR = new JLabel("@ email:nhz94259@163.com");
        jLabe_AUTHOR.setHorizontalAlignment(SwingConstants.CENTER);
        jLabe_AUTHOR.setFont(new Font("宋体", Font.BOLD, 16));
        jLabe_AUTHOR.setBounds(460, 565, 300, 23);
        contentPane.add(jLabe_AUTHOR);

        jLabe_DESCRIPTION = new JLabel("说明：源码路径是版本管理工具导出的增量包路径，编译文件生成于增量包中，需要删除源文件请下拉框处选择");
        jLabe_DESCRIPTION.setHorizontalAlignment(SwingConstants.LEFT);
        jLabe_DESCRIPTION.setFont(new Font("宋体", Font.BOLD,  12));
        jLabe_DESCRIPTION.setBounds(20, 15, 700, 23);
        contentPane.add(jLabe_DESCRIPTION);

        JLabel label_1 = new JLabel("源码路径:");
        label_1.setHorizontalAlignment(SwingConstants.LEFT);
        label_1.setFont(new Font("宋体", Font.BOLD, 16));
        label_1.setBounds(20, 65, 150, 23);
        contentPane.add(label_1);

        jTextField_UP_1 = new JTextField();//文本框
        jTextField_UP_1.setBounds(145, 55, 460, 40);
        jTextField_UP_1.setColumns(10);
        jTextField_UP_1.setFont(new Font("宋体",Font.BOLD,20));
        contentPane.add(jTextField_UP_1);

        JLabel label_2 = new JLabel("编译文件路径:");
        label_2.setHorizontalAlignment(SwingConstants.LEFT);
        label_2.setFont(new Font("宋体", Font.BOLD, 16));
        label_2.setBounds(20, 135, 150, 23);
        contentPane.add(label_2);

        jTextField_UP_2 = new JTextField();//文本框
        jTextField_UP_2.setBounds(145, 125, 460, 40);
        jTextField_UP_2.setColumns(10);
        jTextField_UP_2.setFont(new Font("宋体",Font.BOLD,20));
        contentPane.add(jTextField_UP_2);
        //class cppy 输出
        JTextArea textArea = new JTextArea();//文本域
        textArea.setBackground(SystemColor.text);
        textArea.setBounds(20, 200, 740, 350);
        textArea.setFont(new Font("宋体",Font.BOLD,15));
        textArea.setLineWrap(true);
        contentPane.add(textArea);
        //竖向滚动条

        scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(20, 200, 740, 350);
        contentPane.add(scrollPane_1);
        scrollPane_1.setViewportView(textArea );
        //目标尾椎
        Jtailf1 = new JTextField();//文本框
        Jtailf1.setBounds(550, 50, 60, 40);
        Jtailf1.setColumns(10);
        Jtailf1.setFont(new Font("宋体",Font.BOLD,20));
        //contentPane.add(Jtailf1);
        //编译尾椎
        Jtailf2 = new JTextField();//文本框
        Jtailf2.setBounds(550, 120, 60, 40);
        Jtailf2.setColumns(10);
        Jtailf2.setFont(new Font("宋体",Font.BOLD,20));
        //contentPane.add(Jtailf2);




        JButton ok = new JButton("CPPY ");
        ok.addActionListener(new ActionListener() {//监听事件
            public void actionPerformed(ActionEvent arg0) {
                String java_path=jTextField_UP_1.getText();
                String class_path=jTextField_UP_2.getText();

                textArea.setText(class_path+ java_path);//初始化文本域内容为空

               File f1 = new File(java_path.trim());
               File f2 = new File(class_path.trim());
                 //File f1 = new File("C:\\Users\\wiz\\Desktop\\branches\\PFYH\\web\\src\\com");
                // File f2 = new File("C:\\server\\jboss\\jboss-6.0.0.Final\\server\\default\\deploy\\shpf-web.war\\WEB-INF\\classes\\com");
                ClassSvnTool tool = new ClassSvnTool();
                tool.initCount();
                if (f1.exists() && f2.exists()) {
                    tool.setTarget_Path(f1.getPath());
                    tool.setClass_Path(f2.getPath());
                    tool.setDelete( jComboBox.getSelectedItem().equals("删除源文件"));
                    tool.createPath(f1);
                    textArea.setText(tool.getRESULT()+"\n\n"+"共复制了" + tool.count + "个文件");
                }else {
                    textArea.setText("路径有误");
                }

            }
        });
        ok.setBackground(new Color(255, 250  , 255));
        ok.setBounds(620, 55, 126, 40);
        contentPane.add(ok);

        String [] yn = {"保留原文件", "删除源文件"};
        // 下拉框
        jComboBox = new JComboBox(yn);
        jComboBox.setBounds(620, 125, 126, 40);
        contentPane.add(jComboBox);
    }
}