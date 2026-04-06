import { execSync } from "node:child_process";
import { readFileSync, existsSync } from "node:fs";
import { resolve, dirname } from "node:path";
import { fileURLToPath } from "node:url";

const __dirname = dirname(fileURLToPath(import.meta.url));
const ROOT_DIR = resolve(__dirname, "..");

// JAVA_HOME 환경변수 확인 (gradlew 실행에 필요)
if (!process.env.JAVA_HOME) {
  console.error("❌ 오류: JAVA_HOME 환경변수가 설정되어 있지 않습니다.");
  console.error("      Gradle Wrapper 실행을 위해 JAVA_HOME을 설정해주세요.");
  process.exit(1);
}

// project-folder-list.txt 읽기
const projectListFile = resolve(ROOT_DIR, "project-folder-list.txt");

if (!existsSync(projectListFile)) {
  console.error("오류: project-folder-list.txt 파일을 찾을 수 없습니다.");
  process.exit(1);
}

const projectDirs = readFileSync(projectListFile, "utf-8")
  .split("\n")
  .map((line) => line.trim())
  .filter((line) => line && !line.startsWith("#")); // 빈 줄, 주석 제외

console.log("==========================================");
console.log("🚀 모든 프로젝트 초기화 시작");
console.log("==========================================\n");

const startTime = new Date();
let successCount = 0;
let failedCount = 0;
const failedProjects = [];

projectDirs.forEach((dir) => {
  const projectPath = resolve(ROOT_DIR, dir);

  if (!existsSync(projectPath)) {
    console.log(`⚠️ 경고: 디렉터리 없음 - ${dir}\n`);
    return;
  }

  console.log("==========================================");
  console.log(`📁 [${dir}] 초기화 실행 중...`);
  console.log("==========================================");

  try {
    execSync("corepack use pnpm@latest", {
      cwd: projectPath,
      stdio: "inherit",
      shell: true,
    });

    execSync("pnpm init-project", {
      cwd: projectPath,
      stdio: "inherit",
      shell: true,
    });

    console.log(`\n✅ 성공: ${dir}\n`);
    successCount++;
  } catch (error) {
    // NOSONAR
    console.log(`\n❌ 실패: ${dir}`);
    console.log(`   원인: ${error.message}\n`);
    failedCount++;
    failedProjects.push(dir);
  }
});

const endTime = new Date();
const elapsed = ((endTime - startTime) / 1000).toFixed(1);

console.log("==========================================");
console.log("📊 초기화 결과 요약");
console.log("==========================================");
console.log(`소요 시간: ${elapsed}초`);
console.log(`✅ 성공한 프로젝트: ${successCount}개`);
console.log(`❌ 실패한 프로젝트: ${failedCount}개`);

if (failedCount > 0) {
  console.log(`\n🔍 실패한 프로젝트 목록:`);
  failedProjects.forEach((p) => console.log(`   - ${p}`));
  console.log("\n⚠️  위 프로젝트들을 다시 확인해주세요!");
} else {
  console.log("\n🎉 모든 프로젝트 초기화 성공!");
}

console.log("==========================================");
