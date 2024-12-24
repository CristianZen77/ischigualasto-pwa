from PIL import Image
import os

# Tamaños de íconos requeridos
sizes = [72, 96, 128, 144, 152, 192, 384, 512]

# Crear directorio de íconos si no existe
if not os.path.exists('icons'):
    os.makedirs('icons')

# Abrir la imagen original
img = Image.open('original_icon.png')

# Generar íconos en diferentes tamaños
for size in sizes:
    resized = img.resize((size, size), Image.Resampling.LANCZOS)
    resized.save(f'icons/icon-{size}x{size}.png')
