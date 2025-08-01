@echo off
setlocal enabledelayedexpansion

REM 현재 파일의 위치를 루트 디렉터리로 설정
set "ROOT_DIR=%~dp0"

REM 결과 저장 변수들
set "SUCCESS_COUNT=0"
set "FAILED_COUNT=0"
set "FAILED_PROJECTS="

REM 파일 존재 확인
if not exist "project-folder-list.txt" (
  echo 오류: project-folder-list.txt 파일을 찾을 수 없습니다.
  pause
  exit /b 1
)

echo ==========================================
echo 🚀 모든 프로젝트 테스트 시작
echo ==========================================
echo.

REM 시작 시간 기록
set "START_TIME=%TIME%"

for /F "delims=" %%D in (project-folder-list.txt) do (
  echo ==========================================
  echo 📁 [%%D] 테스트 실행 중...
  echo ==========================================
  cd "%ROOT_DIR%\%%D"
  
  REM gradle clean test 실행
  call gradle clean test
  
  if errorlevel 1 (
    echo.
    echo ❌ 실패: %%D
    set /a FAILED_COUNT+=1
    if "!FAILED_PROJECTS!"=="" (
      set "FAILED_PROJECTS=%%D"
    ) else (
      set "FAILED_PROJECTS=!FAILED_PROJECTS!, %%D"
    )
  ) else (
    echo.
    echo ✅ 성공: %%D
    set /a SUCCESS_COUNT+=1
  )
  
  echo.
  cd "%ROOT_DIR%"
)

REM 종료 시간 기록
set "END_TIME=%TIME%"

echo ==========================================
echo 📊 테스트 결과 요약
echo ==========================================
echo 시작 시간: %START_TIME%
echo 종료 시간: %END_TIME%
echo.
echo ✅ 성공한 프로젝트: %SUCCESS_COUNT%개
echo ❌ 실패한 프로젝트: %FAILED_COUNT%개

if %FAILED_COUNT% gtr 0 (
  echo.
  echo 🔍 실패한 프로젝트 목록:
  echo %FAILED_PROJECTS%
  echo.
  echo ⚠️  위 프로젝트들을 다시 확인해주세요!
) else (
  echo.
  echo 🎉 모든 프로젝트 테스트 성공!
)

echo ==========================================

pause
