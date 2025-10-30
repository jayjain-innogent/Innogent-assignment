/*
  Warnings:

  - You are about to drop the column `createdAt` on the `category` table. All the data in the column will be lost.
  - You are about to drop the column `createdAt` on the `company` table. All the data in the column will be lost.

*/
-- AlterTable
ALTER TABLE `category` DROP COLUMN `createdAt`;

-- AlterTable
ALTER TABLE `company` DROP COLUMN `createdAt`;
