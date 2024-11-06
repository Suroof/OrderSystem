#include "mainwindow.h"
#include "ui_mainwindow.h"
#include "searchdialog.h"
#include "replacedialog.h"
MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    setWindowTitle("新建文本文件-编辑器");
    initUI();
    initConnections();
    setCentralWidget(ui->plainTextEdit);
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::initUI()
{
    //设置字体大小控件
    spinFontSize = new QSpinBox();
    spinFontSize->setMinimum(5);    //最小值5
    spinFontSize->setMaximum(50);   //最大值50
    ui->toolBar->addWidget(new QLabel("字体大小:"));    //先添加一个label提示
    ui->toolBar->addWidget(spinFontSize);     //添加到工具栏

    //设置字体控件
    comboFont = new QFontComboBox();
    ui->toolBar->addWidget(new QLabel("字体:"));
    ui->toolBar->addWidget(comboFont);

    //当前文件控件
    currentFile = new QLabel();
    currentFile->setText("当前文件:");    //设置文本
    ui->statusbar->addWidget(currentFile);    //添加到状态栏
}

void MainWindow::initConnections()
{
    //连接字体spinBox
    connect(spinFontSize,SIGNAL(valueChanged(int)),this,SLOT(on_spinFontSize_valueChanged(int)));
    //连接字体comboBox
    connect(comboFont,SIGNAL(currentIndexChanged(const QString &)),this,SLOT(on_comboFont_currentIndexChanged(const QString &)));
}

void MainWindow::on_actionBold_triggered(bool checked)
{
    QTextCharFormat fmt;
    if(checked)
        fmt.setFontWeight(QFont::Bold);
    else
        fmt.setFontWeight(QFont::Normal);
    ui->plainTextEdit->mergeCurrentCharFormat(fmt);
}

void MainWindow::on_plainTextEdit_copyAvailable(bool b)
{
    ui->actionCut->setEnabled(b);
    ui->actionCopy->setEnabled(b);
    ui->actionPaste->setEnabled(ui->plainTextEdit->canPaste());
}

void MainWindow::on_spinFontSize_valueChanged(int aFontsize)
{
    QTextCharFormat fmt;
    fmt.setFontPointSize(aFontsize);
    ui->plainTextEdit->mergeCurrentCharFormat(fmt);
}

void MainWindow::on_comboFont_currentIndexChanged(const QString &text) {
    QTextCharFormat fmt;
    fmt.setFontFamilies({text});
    ui->plainTextEdit->mergeCurrentCharFormat(fmt);
}

void MainWindow::on_actionItalic_triggered(bool checked)
{
    QTextCharFormat fmt;
    fmt.setFontItalic(checked);
    ui->plainTextEdit->mergeCurrentCharFormat(fmt);
}

void MainWindow::on_actionUnderline_triggered(bool checked)
{
    QTextCharFormat fmt;
    fmt.setFontUnderline(checked);
    ui->plainTextEdit->mergeCurrentCharFormat(fmt);
}

void MainWindow::on_actionColor_triggered()
{
    QPalette pal = ui->plainTextEdit->palette();
    QColor iniColor = pal.color(QPalette::Text);
    QColor color = QColorDialog::getColor(iniColor,this,"选择颜色");
    if(!color.isValid())
        return;

    //pal.setColor(QPalette::Text,color);
    QTextCharFormat fmt;
    fmt.setForeground(color);   //设置字体颜色
    ui->plainTextEdit->mergeCurrentCharFormat(fmt);

    //ui->plainTextEdit->setPalette(pal);

}

void MainWindow::on_actionOpen_triggered()
{
    QString curPath = QDir::currentPath();
    QString filter = "文本文件(*.txt)";
    QString fileName = QFileDialog::getOpenFileName(this,"打开文本文件",curPath,filter);
    if(fileName.isEmpty())
        return;

    QString curFileName = "当前文件:" + fileName;
    currentFile->setText(curFileName);

    QFile file(fileName);
    if(file.open(QIODevice::ReadWrite)){
        ui->plainTextEdit->setPlainText(file.readAll());
        file.close();
    }
}

void MainWindow::on_actionSave_triggered()
{
    QString curPath = QDir::currentPath();
    QString filter = "文本文件(*.txt)";
    QString fileName = QFileDialog::getSaveFileName(this,"另存为",curPath,filter);
    if(fileName.isEmpty())
        return;

    QFile file(fileName);
    if(file.open(QIODevice::WriteOnly)){
        QString content = ui->plainTextEdit->toPlainText();
        QByteArray strBytes = content.toUtf8();
        file.write(strBytes,strBytes.length());
    }
}

void MainWindow::on_acitonNew_triggered(){
    file_path_ = "";
    ui->plainTextEdit->setPlainText("");
}

void MainWindow::on_actionFind_triggered(){
    SearchDialog dlg(this,ui->plainTextEdit);
        dlg.exec();
}

void MainWindow::on_actionReplace_triggered(){
    ReplaceDialog dlg;
    dlg.exec();
}
