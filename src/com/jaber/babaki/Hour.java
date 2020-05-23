package com.jaber.babaki;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.widget.ImageView;
   /*
    * Designed by J. Babaki
    * 1394/04/16
    */

public class Hour extends ImageView {

    private Paint            sandPaint;
    private Paint            LinePaint;
    private int              xFrist        = 120;
    private int              yFrist        = 190;
    private int              tRows         = 250;
    private int              tColumn       = 270;
    private int              Cascade       = 100;
    private int              eachSand      = 10;
    private int              bRows         = 0;
    private int              Roof          = 150;
    private int              finishCascade = 330;
    private int              countEachRow  = 0;
    private int              yu            = 445;
    private boolean          tru           = true;
    private static final int STROKE_WIDTH  = 8;


    public Hour(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize(context);
    }


    public Hour(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }


    public Hour(Context context) {
        super(context);
        initialize(context);
    }


    private void initialize(Context context) {

        sandPaint = new Paint();
        sandPaint.setColor(Color.parseColor("#689E7C"));
        sandPaint.setAntiAlias(true);
        sandPaint.setStrokeWidth(STROKE_WIDTH);
        sandPaint.setStyle(Style.STROKE);

        LinePaint = new Paint();
        LinePaint.setColor(Color.parseColor("#626F80"));
        LinePaint.setAntiAlias(true);
        LinePaint.setStrokeWidth(STROKE_WIDTH);
        LinePaint.setStyle(Style.STROKE);

        updateBottom();
        updateTop();

    }


    private void updateTop() {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {

                while (tru) {
                    try {
                        Thread.sleep(1800);

                        if (yFrist + 140 < 445) {
                            yFrist += 10;
                            tRows -= 5;
                        } else {
                            yFrist += 10;
                            xFrist += 10;
                            tColumn -= 20;
                        }
                        postInvalidate();
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        });
        thread.start();

    }


    private void updateBottom() {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (tru) {
                    try {
                        Thread.sleep(100);
                        if (yFrist < 420) {
                            if (Cascade <= finishCascade) {
                                Cascade += 30;
                            } else {
                                if (eachSand >= Roof) {
                                    bRows = bRows + 10;
                                    countEachRow = 0;
                                    Roof = Roof + 150;
                                    Cascade -= 10;
                                    finishCascade -= 50;
                                }
                            }
                        } else {
                            yu = yu + 5;
                            Cascade -= 5;
                            if (yFrist == 450) {
                                tru = false;
                            }
                        }
                        eachSand = eachSand + 10;
                        postInvalidate();

                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        thread.start();

    }


    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawText("Tik && Jab", 100, 100, sandPaint);
        drawLine(canvas, 100, 100, 100, 300);
        drawLine(canvas, 100, 300, 230, 445);
        drawLine(canvas, 230, 455, 100, 600);
        drawLine(canvas, 100, 600, 100, 800);
        drawLine(canvas, 100, 800, 250, 820);
        drawLine(canvas, 250, 820, 400, 800);
        drawLine(canvas, 400, 800, 400, 600);
        drawLine(canvas, 400, 600, 270, 455);
        drawLine(canvas, 270, 445, 400, 300);
        drawLine(canvas, 400, 300, 400, 100);
        drawLine(canvas, 400, 100, 250, 80);
        drawLine(canvas, 250, 80, 100, 100);
        //  Log.i("LOG", "" + yFrist);
        int topsyTurvy = 0;
        for (int i = 0; i < tRows; i += 10)
        {
            if (300 < yFrist + i) {
                topsyTurvy += 10;
            }
            for (int t = 0 + topsyTurvy; t < tColumn - topsyTurvy; t += 10) {

                if ( !((i < 30) && t > (tColumn / 2) - 80 + i * 3 && t < (tColumn / 2) + 70 - i * 3))
                    canvas.drawPoint(xFrist + t, yFrist + i, sandPaint);

            }
        }
        for (int x = 0; x < Cascade; x += 10) {
            int randomY = (int) Math.floor(Math.random() * 20);
            canvas.drawPoint(240 + randomY, yu + x, sandPaint);

        }
        int checkNextRow = eachSand;
        if (checkNextRow > 150) {
            countEachRow += 1;
        }
        int vb = 0;
        int nm = 0;
        if (Cascade > finishCascade) {
            for (int x = 0; x <= bRows; x += 10) {
                if (x == ((eachSand / 150) * 10) && x != 0) {
                    checkNextRow = 10 * countEachRow;
                }
                else if (checkNextRow > 150) {
                    checkNextRow = 150;
                }
                if (640 > nm) {
                    vb += 10;
                }
                nm = 770 - x;
                for (int t = 0; t < checkNextRow - vb; t += 10) {
                    canvas.drawPoint(250 - t, 770 - x, sandPaint);
                }
                for (int t = 0; t < checkNextRow - vb; t += 10) {
                    canvas.drawPoint(250 + t, 770 - x, sandPaint);
                }

            }

        }

    }


    private void drawLine(Canvas canvas, int xa, int ya, int xb, int yb) {
        int dx = xb - xa, dy = yb - ya, step = 0, k;
        float xInc, yInc, x = xa, y = ya;
        if (Math.abs(dx) > Math.abs(dy))
        {
            step = Math.abs(dx);
        } else {
            step = Math.abs(dy);
        }
        xInc = dx / (float) step;
        yInc = dy / (float) step;
        canvas.drawPoint(x, y, LinePaint);
        for (k = 0; k < step; k++) {
            x += xInc;
            y += yInc;
            canvas.drawPoint(x, y, LinePaint);
        }
    }
}
