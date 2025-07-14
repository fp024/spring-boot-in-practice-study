@echo off

REM 현재 파일의 위치를 루트 디렉터리로 설정
set "ROOT_DIR=%~dp0"

REM 파일 존재 확인
if not exist "project-folder-list.txt" (
  echo 오류: project-folder-list.txt 파일을 찾을 수 없습니다.
  pause
  exit /b 1
)

REM 모든 하위 프로젝트에서 gradle wrapper 실행
for /F "delims=" %%D in (project-folder-list.txt) do (
  @echo [%%D]
  @cd "%ROOT_DIR%\%%D"
  @call gradle wrapper
  if errorlevel 1 (
    echo 오류: %%D에서 gradle wrapper 실행 실패
  )
)

@cd "%ROOT_DIR%"
@echo 모든 gradle wrapper 작업이 완료되었습니다. 