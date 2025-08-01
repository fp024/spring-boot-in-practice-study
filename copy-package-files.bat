@echo off

REM 현재 파일의 위치를 루트 디렉터리로 설정
set "ROOT_DIR=%~dp0"

REM 파일 존재 확인
if not exist "project-folder-list.txt" (
  echo 오류: project-folder-list.txt 파일을 찾을 수 없습니다.
  pause
  exit /b 1
)

REM 복사할 파일들 존재 확인
if not exist "%ROOT_DIR%package.json" (
  echo 오류: package.json 파일을 찾을 수 없습니다.
  pause
  exit /b 1
)

if not exist "%ROOT_DIR%pnpm-lock.yaml" (
  echo 오류: pnpm-lock.yaml 파일을 찾을 수 없습니다.
  pause
  exit /b 1
)

echo 복사할 파일들:
echo - %ROOT_DIR%package.json
echo - %ROOT_DIR%pnpm-lock.yaml
echo.

REM 모든 하위 프로젝트에 package.json과 pnpm-lock.yaml 복사
for /F "delims=" %%D in (project-folder-list.txt) do (
  echo [%%D] 복사 중...
  
  REM 대상 디렉터리 존재 확인
  if exist "%ROOT_DIR%\%%D" (
    copy "%ROOT_DIR%package.json" "%ROOT_DIR%\%%D\" >nul
    if errorlevel 1 (
      echo   오류: %%D에 package.json 복사 실패
    ) else (
      echo   ✓ package.json 복사 완료
    )
    
    copy "%ROOT_DIR%pnpm-lock.yaml" "%ROOT_DIR%\%%D\" >nul
    if errorlevel 1 (
      echo   오류: %%D에 pnpm-lock.yaml 복사 실패
    ) else (
      echo   ✓ pnpm-lock.yaml 복사 완료
    )
  ) else (
    echo   경고: 디렉터리 %%D가 존재하지 않습니다.
  )
  echo.
)

echo 모든 package 파일 복사 작업이 완료되었습니다.
pause