import { readFileSync, writeFileSync } from "node:fs";
import { glob } from "glob";

// 루트 package.json에서 packageManager 읽기
const rootPackage = JSON.parse(readFileSync("package.json", "utf-8"));
const packageManager = rootPackage.packageManager;

console.log(`Syncing packageManager: ${packageManager}\n`);

// 모든 하위 프로젝트의 package.json 찾기
const packageFiles = glob.sync("chap*/**/package.json", {
  ignore: ["**/node_modules/**", "**/dist/**", "**/build/**", "**/bin/**"],
});

packageFiles.forEach((file) => {
  const pkg = JSON.parse(readFileSync(file, "utf-8"));

  if (pkg.packageManager === packageManager) {
    console.log(`- Skipped ${file} (already up-to-date)`);
  } else {
    pkg.packageManager = packageManager;
    writeFileSync(file, JSON.stringify(pkg, null, 2) + "\n");
    console.log(`✓ Updated ${file}`);
  }
});

console.log("\n✓ All package.json files synced!");
