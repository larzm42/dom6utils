magick mogrify -size 128x128 -format png ^
	-transparent "#000000" ^
	-fill "rgba( 0, 0, 0, 0.5)" -opaque "#f800f8" ^
	-bordercolor none -border 1x1 -trim ^
	-geometry "128x128>" ^
	-flip ^
	*.tga
