FOR /f "delims=" %%i IN ('DIR *.png /b') DO magick compare -metric RMSE %%i ..\..\..\dom6inspector\images\sprites\%%i NULL:
