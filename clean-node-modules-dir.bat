@echo off
setlocal enabledelayedexpansion

REM 현재 파일의 위치를 루트 디렉터리로 설정
set "ROOT_DIR=%~dp0"

REM 어떤 경로 이하의 node_modules 디렉토리가 전부 삭제될 것인지 출력합니다
echo Deleting all node_modules directories under: %ROOT_DIR%

REM 루트 디렉토리 확인
echo Press Enter key to continue...
set /p dummyVar=""

REM 모든 하위 프로젝트의 node_modules 폴더를 삭제합니다
for /r "%ROOT_DIR%" %%d in (node_modules) do (
    if exist "%%d" (
        REM 현재 디렉토리에서의 상대 경로를 계산
        set "REL_PATH=%%d"
        set "REL_PATH=!REL_PATH:%ROOT_DIR%=!"

        REM 루트 디렉토리와 동일한 수준의 node_modules는 제외
        if not "!REL_PATH!"=="node_modules" (
            echo Deleting %%d
            rd /s /q "%%d"
        ) else (
            echo Skipping %%d as it is at the root level
        )
    )
)

echo Press Enter key to exit...
set /p dummyVar=""

endlocal
