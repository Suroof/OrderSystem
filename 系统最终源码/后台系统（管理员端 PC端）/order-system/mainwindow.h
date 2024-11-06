#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QSpinBox>
#include <QFontComboBox>
#include <QLabel>
#include <QColorDialog>
#include <QFileDialog>

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = 0);
    ~MainWindow();

private slots:
    void on_actionBold_triggered(bool checked);

    void on_plainTextEdit_copyAvailable(bool b);

    void on_spinFontSize_valueChanged(int aFontsize);

    void on_comboFont_currentIndexChanged(const QString &text);

    void on_actionItalic_triggered(bool checked);

    void on_actionUnderline_triggered(bool checked);

    void on_actionColor_triggered();

    void on_actionOpen_triggered();

    void on_actionSave_triggered();

    void on_acitonNew_triggered();

private:
    Ui::MainWindow *ui;
    QSpinBox *spinFontSize;
    QFontComboBox *comboFont;
    QLabel *currentFile;
    QString file_path_;


    void initUI();
    void initConnections();
};

#endif // MAINWINDOW_H
