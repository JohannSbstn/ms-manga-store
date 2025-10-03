#!/bin/sh

# Ruta al hook final que Git ejecutará
GIT_HOOK_FILE=".git/hooks/pre-commit"

# Ruta a nuestro script personalizado que queremos añadir
CUSTOM_HOOK_FILE="src/main/resources/hooks/pre-commit"

# Comando que queremos asegurar que exista en el hook de Git
HOOK_COMMAND="sh ${CUSTOM_HOOK_FILE}"

# Crear el directorio de hooks si no existe
mkdir -p .git/hooks

# Verificar si el hook principal ya existe
if [ ! -f "${GIT_HOOK_FILE}" ]; then
    # Si no existe, lo creamos con el shebang y nuestro comando
    echo "#!/bin/sh" > "${GIT_HOOK_FILE}"
    echo "${HOOK_COMMAND}" >> "${GIT_HOOK_FILE}"
else
    # Si ya existe, verificamos si nuestro comando ya fue añadido
    if ! grep -q "${HOOK_COMMAND}" "${GIT_HOOK_FILE}"; then
        # Si no está, lo añadimos al final
        echo "\n# Added by Maven exec-plugin" >> "${GIT_HOOK_FILE}"
        echo "${HOOK_COMMAND}" >> "${GIT_HOOK_FILE}"
    fi
fi

# Hacemos que el script sea ejecutable
chmod +x "${GIT_HOOK_FILE}"

echo "✅ Git hook 'pre-commit' configured successfully."