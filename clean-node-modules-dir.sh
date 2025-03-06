#!/bin/bash

# 현재 스크립트의 위치를 ROOT_DIR로 설정합니다
ROOT_DIR="$(dirname "$(realpath "$0")")"

# 어떤 경로 이하의 node_modules 디렉토리가 전부 삭제될 것인지 출력합니다
echo "Deleting all node_modules directories under: $ROOT_DIR"

# 디렉토리 확인을 위해 일시 정지
read -p "Press any key to continue..."

# 루트 디렉토리와 동일 수준의 node_modules를 제외하고 하위 디렉토리의 node_modules 폴더를 삭제합니다
find "$ROOT_DIR" -mindepth 2 -type d -name "node_modules" -print -exec rm -rf {} +

# 완료 후 일시 정지
read -p "Press any key to exit..."
